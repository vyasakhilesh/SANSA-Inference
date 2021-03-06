package net.sansa_stack.inference.spark.forwardchaining

import scala.collection.mutable

import org.apache.spark.rdd.RDD

import net.sansa_stack.inference.data.RDFTriple
import net.sansa_stack.inference.spark.data.model.RDFGraph
import net.sansa_stack.inference.utils.Profiler

/**
  * A forward chaining based reasoner.
  *
  * @author Lorenz Buehmann
  */
trait ForwardRuleReasoner extends Profiler {

  /**
    * Applies forward chaining to the given RDF graph and returns a new RDF graph that contains all additional
    * triples based on the underlying set of rules.
    *
    * @param graph the RDF graph
    * @return the materialized RDF graph
    */
  def apply(graph: RDFGraph) : RDFGraph

  /**
    * Extracts all triples for the given predicate.
    *
    * @param triples the triples
    * @param predicate the predicate
    * @return the set of triples that contain the predicate
    */
  def extractTriples(triples: mutable.Set[RDFTriple], predicate: String): mutable.Set[RDFTriple] = {
    triples.filter(triple => triple.p == predicate)
  }

  /**
    * Extracts all triples for the given predicate.
    *
    * @param triples the RDD of triples
    * @param predicate the predicate
    * @return the RDD of triples that contain the predicate
    */
  def extractTriples(triples: RDD[RDFTriple], predicate: String): RDD[RDFTriple] = {
    triples.filter(triple => triple.p == predicate)
  }

  /**
    * Extracts all triples that match the given subject, predicate and object if defined.
    *
    * @param triples the RDD of triples
    * @param subject the subject
    * @param predicate the predicate
    * @param obj the object
    * @return the RDD of triples that match
    */
  def extractTriples(triples: RDD[RDFTriple],
                     subject: Option[String],
                     predicate: Option[String],
                     obj: Option[String]): RDD[RDFTriple] = {
    var extractedTriples = triples

    if(subject.isDefined) {
      extractedTriples = extractedTriples.filter(triple => triple.s == subject.get)
    }

    if(predicate.isDefined) {
      extractedTriples = extractedTriples.filter(triple => triple.p == predicate.get)
    }

    if(obj.isDefined) {
      extractedTriples = extractedTriples.filter(triple => triple.o == obj.get)
    }

    extractedTriples
  }
}
